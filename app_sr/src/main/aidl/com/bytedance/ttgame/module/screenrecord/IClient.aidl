// IClient.aidl
package com.bytedance.ttgame.module.screenrecord;

// Declare any non-default types here with import statements

interface IClient {
    void onResult(int cmd, int error);
}
