package org.activehome.context.live;

/*
 * #%L
 * Active Home :: Context :: Live
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2016 Active Home Project
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
 * An Active Home context managing only live data (no data store).
 *
 * @author Jacky Bourgeois
 */
@ComponentType(version = 1, description = "An Active Home context "
        + "managing only live data (no data storage).")
public class LiveContext extends Context {

    /**
     * Where to find the sources (for the Active Home store).
     */
    @Param(defaultValue = "/active-home-context-live")
    private String src;

    @Override
    public final void onInit() {
        super.onInit();
        sendNotif(new Notif(getFullId(), "*",
                getCurrentTime(), Status.READY.name()));
    }

    /**
     * Save new data point in long term memory.
     *
     * @param dp the new data point
     */
    @Override
    protected void save(final DataPoint dp) {

    }

    /**
     * Save new data point in long term memory.
     *
     * @param dpArray the array of new data points
     */
    @Override
    protected void save(final List<DataPoint> dpArray) {

    }

    /**
     * Provide the last DataPoint received of the given metrics.
     *
     * @param metricArray the metrics we are looking for
     * @param callback    The callback to reply
     */
    @Override
    public void getLastData(final String[] metricArray,
                            final RequestCallback callback) {
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
    public void getData(final String metric,
                        final long start,
                        final long end,
                        final RequestCallback callback) {

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
