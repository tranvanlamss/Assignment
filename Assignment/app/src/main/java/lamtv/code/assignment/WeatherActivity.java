package lamtv.code.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    private TextView tvTem;
    private TextView tvStatus;
    private RecyclerView rvHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvTem = findViewById(R.id.tvTem);
        tvStatus = findViewById(R.id.tvStatus);

        getHours();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvHour = findViewById(R.id.rvHour) ;
        rvHour.setLayoutManager(layoutManager);
    }
    private void getHours(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.getHour().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if(response.body() != null) return;

                List<Weather> listWeather = response.body();
                HourAdapter adapter = new HourAdapter(WeatherActivity.this, listWeather);
                rvHour.setAdapter(adapter);

                Weather weather = listWeather.get(0);
                tvTem.setText(weather.getTemperature().getValue().intValue()+"");
                tvStatus.setText(weather.getIconPhrase());

            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });
    }
}