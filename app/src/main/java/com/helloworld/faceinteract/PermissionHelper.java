package com.helloworld.faceinteract;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

public class PermissionHelper
{
    private AppCompatActivity context;
    private SparseArray<Runnable> permissions = new SparseArray<>();
    PermissionHelper(AppCompatActivity activity)
    {
        context = activity;
    }
    public void requestPermission(String permission, Runnable action)
    {
        permissions.put(permission.hashCode(), action);
        if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
        {
            context.requestPermissions(new String[]{permission}, permission.hashCode());
        }
        else
        {
            action.run();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults)
    {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Runnable action = permissions.get(requestCode);
            action.run();
        }
    }
}
