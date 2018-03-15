package org.peace.allinone.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.ele.base.utils.AppLogger;
import org.peace.allinone.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.start_btn)
  public void onClick(View v) {
    if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1);
    } else {
      locate();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @SuppressLint("MissingPermission")
  void locate() {
    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
      @Override
      public void onLocationChanged(Location location) {
        AppLogger.e("location: " + location);
      }

      @Override
      public void onStatusChanged(String provider, int status, Bundle extras) {
          AppLogger.e("onStatusChanged");
      }

      @Override
      public void onProviderEnabled(String provider) {
        AppLogger.e("onProviderEnabled");
      }

      @Override
      public void onProviderDisabled(String provider) {
        AppLogger.e("onProviderDisabled");
      }
    });

  }
}
