package com.example.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAskPermissions, btnCheckPermissions;
    private final int PERMISSION_REQUEST_CODE = 100;
    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAskPermissions = findViewById(R.id.btnAskPermissions);
        btnCheckPermissions = findViewById(R.id.btnCheckPermissions);

        btnAskPermissions.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!PermissionManager.getInstance(MainActivity.this).checkPermissions(permissions)) {
                    PermissionManager.getInstance(MainActivity.this).askPermissions(MainActivity.this,
                            permissions, PERMISSION_REQUEST_CODE);
                } else {
                    Toast.makeText(MainActivity.this, "Permission already granted!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCheckPermissions.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (PermissionManager.getInstance(MainActivity.this).checkPermissions(permissions)) {
                    Toast.makeText(MainActivity.this, "Permissions already granted.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please request permissions.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override public void onRequestPermissionsResult(int requestCode,
                                                     @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            PermissionManager.getInstance(MainActivity.this).handlePermissionResult(MainActivity.this,
                    PERMISSION_REQUEST_CODE,
                    permissions,
                    grantResults);
        }
    }
}