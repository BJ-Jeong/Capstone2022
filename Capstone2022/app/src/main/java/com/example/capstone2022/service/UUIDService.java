package com.example.capstone2022.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class UUIDService {

    private final Context context;

    public UUIDService(Context context) {
        this.context = context;
    }

    public UUID getUUID() {
        UUID uuid = findUUID();
        if (uuid != null) return uuid;

        newUUID();
        return findUUID();
    }

    @Nullable
    private UUID findUUID() {
        UUID uuid = null;

        try (
                FileInputStream fis = context.openFileInput("uuid.txt");
                DataInputStream dis = new DataInputStream(fis)
        ) {
            uuid = UUID.fromString(dis.readUTF());
        } catch (FileNotFoundException e) {
            Log.w("UUIDService", "UUID file not found");
        } catch (IOException e) {
            Toast.makeText(context, "UUID 조회에 실패 했습니다.", Toast.LENGTH_SHORT).show();
            Log.e("UUIDService", "Failed to get UUID from storage", e);
        } catch (IllegalArgumentException e) {
            Log.e("UUIDService", "Failed to parse UUID");
        }
        return uuid;
    }

    public void newUUID() {
        setUUID(UUID.randomUUID());
    }

    public void setUUID(@NonNull UUID uuid) {
        Log.d("UUIDService", "Set uuid to " + uuid);

        try (
                FileOutputStream fos = context.openFileOutput("uuid.txt", Context.MODE_PRIVATE);
                DataOutputStream dos = new DataOutputStream(fos)
        ) {
            dos.writeUTF(uuid.toString());
            dos.flush();
        } catch (IOException e) {
            Toast.makeText(context, "UUID 설정에 실패 했습니다.", Toast.LENGTH_SHORT).show();
            Log.e("UUIDService", "Failed to set UUID from storage", e);
        }
    }

}
