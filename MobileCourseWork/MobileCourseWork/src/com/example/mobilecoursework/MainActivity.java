package com.example.mobilecoursework;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends Activity implements OnClickListener, LocationListener {

	//Create a GoogleMap
	private GoogleMap map;
	
	//Variables
	private ImageButton hybrid;
	private Button previousGames;
	private LocationManager locationManager;
	private String provider;
	private EditText editLng;
	private EditText editLat;
	private String strLat;
	private String strLng;
	private Button userLocation;
	
	//Coordinates to be used for previous venues
	LatLng Christchurch = new LatLng(-43.53, 172.620278);
	LatLng Edmonton = new LatLng(53.5333, -113.5000);
	LatLng Brisbane = new LatLng(-37.813611, 144.963056);
	LatLng Edinburgh = new LatLng(55.939, -3.172);
	LatLng Auckland = new LatLng(-36.840471, 174.739869);
	LatLng Victoria = new LatLng(48.428611, -123.365556);
	LatLng KualaLumpur = new LatLng(3.1475, 101.693333);
	LatLng Manchester = new LatLng(53.466667, -2.233333);
	LatLng Melbourne = new LatLng(-37.813611, 144.963056);
	LatLng Delhi = new LatLng(28.61, 77.23);
	LatLng GLASGOW = new LatLng (55.8580,-4.2590);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
       // Retrieve the map
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		//Allows zoom controls
		map.getUiSettings().setZoomControlsEnabled(true);
		//Sets the icon for GPS to appear
		map.getUiSettings().setMyLocationButtonEnabled(true);
		//Allows the GPS to work
		map.setMyLocationEnabled(true);
		
		//Finds the edit text boxes by checking their id against the one set out in the xml
		editLng = (EditText) findViewById(R.id.user_lng);
		editLng.setBackgroundColor(Color.BLACK);
		editLat = (EditText) findViewById(R.id.user_lat);
		editLat.setBackgroundColor(Color.BLACK);
		userLocation = (Button) findViewById(R.id.user_location);
		//Registers a callback to be made if this box is clicked
		userLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Returns the text this text view is displaying
				strLng = (editLng.getText().toString());
				strLat = (editLat.getText().toString());
				//This will parse the strLat and the strLng string as a double value
				double numLat = Double.parseDouble(strLat);
				double numLng = Double.parseDouble(strLng);
				
				//If the user location button is clicked
				if(v == userLocation)
				{
					//Construct a new LatLng with the latitude and longitude given by the user
					LatLng pos = new LatLng(numLat,numLng);
					
					//Move the camera the the new position and zoom to a level of 1
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 1));
					
					//Add a marker to this position with the title specififed
					map.addMarker(new MarkerOptions()
					.position(pos)
					.title("Your Location"));
				}
				
			}
		});
		
		
		
		
		
		
		//Get the location manager
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		//Defines how to select the location provider
		Criteria criteria = new Criteria();
		//Returns the best provider for the criteria
		provider = locationManager.getBestProvider(criteria, false);
		//Returns a location from the last location lock from the provider
		Location location = locationManager.getLastKnownLocation(provider);
		
		//Initialise the location 
		
		//If the location doesn't equal 0
		if(location != null){
			//Print out a string with the following text and a new line
			System.out.println("Provider " + provider + " has been selected.");
			//This will be called when the location has been changed
			onLocationChanged(location);
		} 
	
		
		
		
		// Markers for Glasgow games

		//Add a marker
		map.addMarker(
				new MarkerOptions()
				         //Latitude and Longitude of venue
						.position(new LatLng(55.8607, -4.2871))
						//Keeps the marker flat if the map is rotated
						.flat(true)
						//Assigns a custom icon instead of the default marker 
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.boxing))
						//Adds a title to the marker when clicked on
						.title("Scottish Exhibition and Conference Centre"))
						//Adds text underneath the title when clicked on
				.setSnippet(
						"Events: Boxing, Gymnastics, Judo, Netball, Wrestling, Weightlifting");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(56.499, -2.7543))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.shooting))
						.title("Barry Buddon Shooting Centre")).setSnippet(
				"Events: Shooting");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.8497, -4.2055))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.icin))
						.title("Celtic Park")).setSnippet(
				"Events: Opening Ceremony");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.79434, -4.2193))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.athletics))
						.title("Cathkin Braes")).setSnippet(
				"Events: Mountain Bike");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.847, -4.2076))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.badminton))
						.title("Velodrome")).setSnippet(
				"Events: Badminton, Cycling");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.8447, -4.236))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.hockey))
						.title("International Hockey")).setSnippet(
				"Events: Hockey");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.8255, -4.2520))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.cycling))
						.title("Hampden Park")).setSnippet("Events: Athletics");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.853, -4.309))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.rugbyball))
						.title("Ibrox Stadium")).setSnippet(
				"Events: Rugby Sevens");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.867, -4.2871))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.lawn))
						.title("Kelvingrove Bowls")).setSnippet(
				"Events: Lawn Bowls");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.8813, -4.3405))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.table))
						.title("Scotstoun")).setSnippet(
				"Events: Squash, Table Tennis");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.845, -4.177))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.swim))
						.title("Tolcross")).setSnippet("Events: Swimming");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.7971971, -4.0342997))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.triathlon))
						.title("Strathclyde")).setSnippet("Events: Triathlon");

		map.addMarker(
				new MarkerOptions()
						.position(new LatLng(55.939, -3.172))
						.flat(true)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.diving))
						.title("Edinburgh")).setSnippet("Events: Diving");

		

		// Create buttons to change map type

		// Finds by the Id set in the activity main xml file
		hybrid = (ImageButton) findViewById(R.id.HybridButton);
		// Adds an onClickListener to see if its been selected
		hybrid.setOnClickListener(new View.OnClickListener() {
			
			//Declare an int called clicks and set it's value to 0
			private int clicks = 0;
			@Override
			public void onClick(View v) {
				
				//Declare an int called licked that increments clicks by 1
				int licked = ++clicks;
			    
				//If the hybrid button is clicked, licked will equal 0 and the hybrid map will display
				if (v == hybrid) {
					if (licked == 0)
					map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
					
				}

				//If it is clicked again licked will now equal 1 and the terrain map will display
				if (licked == 1)
				{
					map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				}
				
				//If it is clicked again licked will now equal 2 and the satellite map will display
				if (licked == 2)
				{
					map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				}

				//If it is clicked again licked will now equal 3 and the normal map will display
				//clicks will then be set to -1 to return back to the hybrid map if clicked again
				if	(licked == 3)
				{
					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				 	clicks = -1;
				}
				
			}
		});
		
		//Finds the button by checking it's id against the one set out in the xml
		previousGames = (Button) findViewById(R.id.PreviousGamesButton);
		//Registers a callback to be made if this box is clicked
		previousGames.setOnClickListener(this);
		
		

	}

	//Create an int called count and set it to 0
	private int count = 0;
	
	
	@Override
	public void onClick(View arg0) {
		
		//a count that increases every time the Previous games button is clicked
		int counted = ++count;
		
		// Markers for previous games
		
		// If the preious games button is clicked go in to this method
		if (arg0 == previousGames) {
			
			//Start at Glasgow
			if (counted == 0);
			{
				map.animateCamera(CameraUpdateFactory.newLatLng(GLASGOW));
				
			}
			// If the counted list is equal to ten set a marker at Christchurch 
			//Registers a callback to be made if this box is clicked
			
			
			if (counted == 10){
				map.addMarker(
						new MarkerOptions()
								.position(Christchurch)
								.flat(true)
								//Registers a callback to be made if this box is clicked
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.newzealand))
										//Display this information if the marker is clicked
								.title("Christchurch, New Zealand 1974 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 19");
				map.animateCamera(CameraUpdateFactory.newLatLng(Christchurch));
				//Reset the count list to -1 to take the camera back to Glasgow next time the button's pressed
				count = -1;
				
			}
			if (counted == 9){
				map.addMarker(
						new MarkerOptions()
								.position(Edmonton)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.canada))
								.title("Edmonton, Canada 1978 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 14");
				map.animateCamera(CameraUpdateFactory.newLatLng(Edmonton));
				
			}
			
			if (counted == 8){
				map.addMarker(
						new MarkerOptions()
								.position(Brisbane)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.australia))
								.title("Brisbane, Australia 1982 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 26");
				map.animateCamera(CameraUpdateFactory.newLatLng(Brisbane));
			}
			
			if (counted == 7){
				map.addMarker(
						new MarkerOptions()
								.position(Edinburgh)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.scotland))
								.title("Edinburgh, Scotland 1986 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 36");
				map.animateCamera(CameraUpdateFactory.newLatLng(Edinburgh));
			}
			
			if(counted == 6){
				map.addMarker(
						new MarkerOptions()
								.position(Auckland)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.newzealand))
								.title("Auckland, New Zealand 1990 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 22");
				map.animateCamera(CameraUpdateFactory.newLatLng(Auckland));
			}
			
			if(counted == 5){
				map.addMarker(
						new MarkerOptions()
								.position(Victoria)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.canada))
								.title("Victoria, Canada 1994 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 20");
				map.animateCamera(CameraUpdateFactory.newLatLng(Victoria));
			}
			
			if (counted == 4){
				map.addMarker(
						new MarkerOptions()
								.position(KualaLumpur)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.malaysia))
								.title("Kuala Lumpur, Malaysia 1998 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 12");
				map.animateCamera(CameraUpdateFactory.newLatLng(KualaLumpur));
			
		}
			if (counted == 3){
				map.addMarker(
						new MarkerOptions()
								.position(Manchester)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.england))
								.title("Manchester, England 2002 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 30");
				map.animateCamera(CameraUpdateFactory.newLatLng(Manchester));
			}
			
			if (counted == 2){
				map.addMarker(
						new MarkerOptions()
								.position(Melbourne)
								.flat(true)
							    .icon(BitmapDescriptorFactory
										.fromResource(R.drawable.australia))
								.title("Melbourne, Australia 2006 Commonwealth Games"))
						.setSnippet("Medals won by Scotland: 29");
				map.animateCamera(CameraUpdateFactory.newLatLng(Melbourne));
			}
			
			if (counted == 1){
				map.addMarker(
					new MarkerOptions()
					.position(Delhi)
					.flat(true)
				    .icon(BitmapDescriptorFactory
							.fromResource(R.drawable.india))
					.title("Delhi, India 2010 Commonwealth Games"))
			.setSnippet("Medals won by Scotland: 26");
				map.animateCamera(CameraUpdateFactory.newLatLng(Delhi));
				
				
			}
			
		}

	}
	
	//Will request updates at start up
	@Override
	protected void onResume() {
		super.onResume();
		//This will send a request for location updates
		locationManager.requestLocationUpdates(provider, 400, 1, this);
		
	}
	
	//This will remove the locationListener updates when the activity is paused
	@Override
	protected void onPause() { 
		super.onPause();
		locationManager.removeUpdates(this);
	}

	//This will be called when the location has been changed
	@Override
	public void onLocationChanged(Location location) {
		//This will get the new latitude and longitude of the position
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
	}

	//This will be called if the user disables the provider
	@Override
	public void onProviderDisabled(String provider) {
		//This will make a toast view with the specified text and show i for a short time
		Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_SHORT).show();
	}

	//This will be called if the user enables the provider
	@Override
	public void onProviderEnabled(String provider) {
		//This will make a toast view with the specified text and show i for a short time
		Toast.makeText(this, "Enabled new provider " + provider, Toast.LENGTH_SHORT).show();
		
	}
	//This will be called if the status of the provider has changed
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
		
	}
}
	
