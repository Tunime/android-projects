package com.example.maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMapClickListener {

	private GoogleMap googleMap;
	double latitude = 12.973763799999999;
	double longitude = 77.61350459999994;
	int count = 0;
	private static Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button1 = (Button) findViewById(R.id.button1);

		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(new LatLng(latitude, longitude)).zoom(12)
						.build();
				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition)); // move to a
																// position

			}
		});
		
		googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			public void onCameraChange(CameraPosition arg0) {
				// googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
				// Toast.makeText(getApplicationContext(), "kljnfjldsn",
				// Toast.LENGTH_LONG).show();
				count++;
				Log.e("counter", "value is:" + count);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (googleMap != null) {
			switch (item.getItemId()) {
			case R.id.normal:
				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				break;
			case R.id.satellite:
				googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				break;
			case R.id.terrain:
				googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				break;
			case R.id.hybrid:
				googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				break;
			}

			return true;
		} else
			return super.onOptionsItemSelected(item);
	}

	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mapFragment)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}

			// create marker
			// MarkerOptions marker = new MarkerOptions().position(new
			// LatLng(latitude, longitude)).title("Hello Maps ");

			// adding marker
			// googleMap.addMarker(marker);

			// CameraPosition cameraPosition = new CameraPosition.Builder()
			// .target(new LatLng(latitude, longitude)).zoom(12).build();
			// googleMap.animateCamera(CameraUpdateFactory
			// .newCameraPosition(cameraPosition)); // move to a position

			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setRotateGesturesEnabled(true);
			googleMap.setOnMapClickListener(this);

			

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	@Override
	public void onMapClick(LatLng latlong) {
		Toast.makeText(getApplicationContext(),
				"Lat:" + latlong.latitude + " Long" + latlong.longitude,
				Toast.LENGTH_LONG).show();

	}

}

//
// 12.973763799999999
// Longitude: 77.61350459999994