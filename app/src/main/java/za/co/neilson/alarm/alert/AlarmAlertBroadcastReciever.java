/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 * I modified part of the contents in Korean.
 * In addition, the menu was added in the scope without erasing the contents.
 * The source of this source is "https://github.com/SheldonNeilson/Android-Alarm-Clock.git"
 */
package za.co.neilson.alarm.alert;

import za.co.neilson.alarm.Alarm;
import za.co.neilson.alarm.service.AlarmServiceBroadcastReciever;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmAlertBroadcastReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent mathAlarmServiceIntent = new Intent(
				context,
				AlarmServiceBroadcastReciever.class);
		context.sendBroadcast(mathAlarmServiceIntent, null);
		
		StaticWakeLock.lockOn(context);
		//알람 아이템 받아옴
		Bundle bundle = intent.getExtras();
		final Alarm alarm = (Alarm) bundle.getSerializable("alarm");
		Intent mathAlarmAlertActivityIntent;

		mathAlarmAlertActivityIntent = new Intent(context, AlarmAlertActivity.class);

		//알람 아이템을 알람 엑트 엑티비티로 쏘아보냄
		mathAlarmAlertActivityIntent.putExtra("alarm", alarm);
		//새로운 창에 실행 `
		mathAlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		//시작
		context.startActivity(mathAlarmAlertActivityIntent);

		//AlarmAlertActivity로 alarm인텐트 이용해서 이동
	}

}
