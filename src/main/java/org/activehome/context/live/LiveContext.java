package org.activehome.context.live;

import com.eclipsesource.json.JsonObject;
import org.activehome.com.Notif;
import org.activehome.com.RequestCallback;
import org.activehome.com.Status;
import org.activehome.context.Context;
import org.activehome.context.com.ContextRequest;
import org.activehome.context.data.DataPoint;
import org.activehome.context.data.UserInfo;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Param;

import java.util.List;

/**
 * @author Jacky Bourgeois
 * @version %I%, %G%
 */
@ComponentType(description = "An Active Home context managing only live data (no data storage).")
public class LiveContext extends Context {

    @Param(defaultValue = "/active-home-context-live")
    private String src;

    @Override
    public void onInit() {
        super.onInit();
        sendNotif(new Notif(getFullId(),"*",
                getCurrentTime(), Status.READY.name()));
    }

    /**
     * Save new data point in long term memory.
     *
     * @param dp the new data point
     */
    @Override
    protected void save(DataPoint dp) {

    }

    /**
     * Save new data point in long term memory.
     *
     * @param dpArray the array of new data points
     */
    @Override
    protected void save(List<DataPoint> dpArray) {

    }

    /**
     * Provide the last DataPoint received of the given metrics.
     *
     * @param metricArray the metrics we are looking for
     * @param callback    The callback to reply
     */
    @Override
    public void getLastData(String[] metricArray,
                            RequestCallback callback) {
        JsonObject requestedData = new JsonObject();
        for (String metric : metricArray) {
            DataPoint dp = getCurrentDP(metric, "0", 0);
            if (dp != null) {
                requestedData.add(metric, dp.toJson());
            }
        }
        callback.success(requestedData);
    }

    /**
     * Provide the DataPoints of a given metric for a given period of time.
     *
     * @param metric   The metric we are looking at
     * @param start    The start time of the period (milliseconds)
     * @param end      The end time of the period (milliseconds)
     * @param callback The callback to reply
     */
    @Override
    public void getData(String metric,
                        long start,
                        long end,
                        RequestCallback callback) {

    }

    /**
     * Extract DataPoints from the context.
     *
     * @param contextRequest The details of the data to extract
     * @param iteration      How many request should be performed
     * @param shift          Length of the time shift between each request
     * @param callback       The callback to reply
     */
    @Override
    public void extractSampleData(final ContextRequest contextRequest,
                                  final int iteration,
                                  final long shift,
                                  final RequestCallback callback) {

    }

    @Override
    public void extractSchedule(final long start,
                                final long duration,
                                final long granularity,
                                final String[] metricArray,
                                final RequestCallback callback) {

    }

    @Override
    public void getAllAvailableMetrics(final UserInfo userInfo,
                                       final RequestCallback callback) {

    }


}
