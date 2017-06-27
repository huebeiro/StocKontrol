package com.huebeiro.stockontrol;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huebeiro.database.DatabaseHelper;
import com.huebeiro.model.Product;
import com.huebeiro.model.ProductType;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        GraphView graphStock = (GraphView) findViewById(R.id.graphStock);
        GraphView graphType = (GraphView) findViewById(R.id.graphType);
        DatabaseHelper helper = new DatabaseHelper(this);

        ArrayList<Product> products = helper.getProductsQuantity();
        ArrayList<DataPoint> points = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for(int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            DataPoint point = new DataPoint(i, product.getQuantity());
            points.add(point);
            labels.add(product.getName());
        }
        DataPoint[] pointsArray = points.toArray(new DataPoint[points.size()]);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(pointsArray);
        series.setSpacing(50);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);
        graphStock.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphStock);
        staticLabelsFormatter.setHorizontalLabels(labels.toArray(new String[labels.size()]));
        graphStock.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        ArrayList<ProductType> types = helper.getMoneyExpent();
        points = new ArrayList<>();
        labels = new ArrayList<>();
        for(int i = 0; i < types.size(); i++){
            ProductType type = types.get(i);
            points.add(new DataPoint(i, type.getPrice()));
            labels.add(type.getName());
        }
        BarGraphSeries<DataPoint> seriesTypes = new BarGraphSeries<>(points.toArray(new DataPoint[points.size()]));
        seriesTypes.setSpacing(50);
        seriesTypes.setDrawValuesOnTop(true);
        seriesTypes.setValuesOnTopColor(Color.BLACK);
        graphType.addSeries(seriesTypes);
        StaticLabelsFormatter typeFomatter = new StaticLabelsFormatter(graphType);
        typeFomatter.setHorizontalLabels(labels.toArray(new String[labels.size()]));
        graphType.getGridLabelRenderer().setLabelFormatter(typeFomatter);
    }


}
