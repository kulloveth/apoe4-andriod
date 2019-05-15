package hng.tech.apoe_4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hng.tech.apoe_4.R;
import hng.tech.apoe_4.models.AnswerState;
import hng.tech.apoe_4.views.TodayView;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>{

    private Context context;
    TodayView todayView;
    private List<AnswerState> options;

    public OptionsAdapter(Context context, TodayView todayView, List<AnswerState> options) {
        this.context = context;
        this.todayView = todayView;
        this.options = options;
    }


    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.answers_list_item, parent, false);

        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        holder.bindTo(options.get(position));
//        holder.answerText.setSelected(false);
//        holder.answerText.setTextColor(context.getResources().getColor(R.color.black));
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    class OptionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.answerText)
        Button answerText;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(AnswerState option){
            answerText.setText(option.getAnswerText());
            answerText.setOnClickListener(v -> {
                answerText.setSelected(true);
                answerText.setTextColor(context.getResources().getColor(R.color.white));
                todayView.onAnswerSelected(getAdapterPosition(), option.getAnswerText());

            });
            if (option.getChosen() == 2) {
                answerText.setEnabled(false);
                answerText.setTextColor(context.getResources().getColor(R.color.white));
//                answerText.setSelected(false);
            } else if (option.getChosen() == 0){
                answerText.setEnabled(true);
                answerText.setSelected(false);
                answerText.setTextColor(context.getResources().getColor(R.color.black));

            }
            else answerText.setSelected(true);

        }

    }
}