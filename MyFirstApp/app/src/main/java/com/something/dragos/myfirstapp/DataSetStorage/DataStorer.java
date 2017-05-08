package com.something.dragos.myfirstapp.DataSetStorage;

import java.util.ArrayList;

/**
 * This data structure will be used to store all of the data sets entered and deleted by the user
 * and will provide support for the user to be able to view their previously deleted data sets
 * Created by Dragos on 1/31/17.
 */

public class DataStorer {

    // to store all the dleted data values:
    private ArrayList <String> dataSets = new ArrayList<String>();
    // to keep track of which data set should be displayed:
    private int possitionOfCurrentDataSet;


    /**
     * Add a data set to the DataStorer:
     * @param data
     */
    public void addData (String data) {

        if (this.dataSets.contains(data)) {
            this.possitionOfCurrentDataSet = this.dataSets.size() - 1;
            return;
        }

        this.dataSets.add(data);
        this.possitionOfCurrentDataSet = this.dataSets.size() - 1;
    }


    /**
     * Move the position of the current data set to display, back by one:
     */
    public void moveBack () {

        if (this.possitionOfCurrentDataSet == 0)
            return;

        this.possitionOfCurrentDataSet--;
    }


    /**
     * Move the position of the current data set to display, forward by one:
     */
    public void moveForward () {
        if (this.possitionOfCurrentDataSet == this.dataSets.size() - 1)
            return;
        this.possitionOfCurrentDataSet++;
    }

    public int getPossitionOfCurrentDataSet () {
        return this.possitionOfCurrentDataSet;
    }

    /**
     * return the currently selected data set
     * or returns null if there are no deleted data sets
     *
     * @return
     */
    public String getCurrentDataSet () {
        return this.dataSets.get(possitionOfCurrentDataSet);
    }

    public boolean isEmpty () {
        return this.dataSets.isEmpty();
    }
}
