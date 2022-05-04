package lamtv.code.assignment;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter{
    private Activity activity;
    private List<Weather> listWeather;

    public HourAdapter(Activity activity, List<Weather> listWeather) {
        this.activity = activity;
        this.listWeather = listWeather;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_hour, parent, false);
        HourHolder holder = new HourHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder vh = (HourHolder) holder;
        Weather weather = listWeather.get(position);
        vh.tvTime.setText(convertTime(weather.getDateTime()));
        vh.tvTem.setText(weather.getTemperature().getValue()+"");
        String url = "";
        if(weather.getWeatherIcon() < 10){
         url = "http://developer.com.accuweather.com/sites/default/files/0" + weather.getWeatherIcon() + "-s.png";
        }else {
            url = "http://developer.com.accuweather.com/sites/default/files" + weather.getWeatherIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(vh.ivCover);
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public class HourHolder extends RecyclerView.ViewHolder{
        private TextView tvTime;
        private ImageView ivCover;
        private TextView tvTem;

        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTem = itemView.findViewById(R.id.tvTem);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}
