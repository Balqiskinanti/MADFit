package sg.edu.np.mad.madfit.Model;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

public class WorkoutDoneDecorator implements DayViewDecorator {
    //workout calendar decorator
    HashSet<CalendarDay> list;
    ColorDrawable colorDrawable;

    public WorkoutDoneDecorator(HashSet<CalendarDay> list) {
        this.list = list;
        colorDrawable = new ColorDrawable(Color.parseColor("#5868E0"));
    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        return list.contains(calendarDay);
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.setBackgroundDrawable(colorDrawable);
    }
}
