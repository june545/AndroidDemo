package cn.cj.mediascanner;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * LoaderManager, CursorLoader, 视频文件浏览
 */
public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        m();
    }

    private void m() {
        // MediaStore.Video.Media.DATA：视频文件路径；
        // MediaStore.Video.Media.DISPLAY_NAME : 视频文件名，如 testVideo.mp4
        // MediaStore.Video.Media.TITLE: 视频标题 : testVideo

        getSupportLoaderManager().initLoader(0, null, loaderCallbacks);

    }

    LoaderManager.LoaderCallbacks<Cursor> loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader loader = new CursorLoader(getApplicationContext());
            loader.setUri(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            String[] mediaColumns = {MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.TITLE,
                    MediaStore.Video.Media.MIME_TYPE,
                    MediaStore.Video.Media.DISPLAY_NAME};
            String[] projection = {MediaStore.Video.VideoColumns._ID,
                    MediaStore.Video.VideoColumns.TITLE,
                    MediaStore.Video.VideoColumns.DISPLAY_NAME,
                    MediaStore.Video.VideoColumns.DATA,
                    MediaStore.Video.VideoColumns.MIME_TYPE};
            loader.setProjection(projection);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            int count = 1;
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                String _title = data.getString(data.getColumnIndex(MediaStore.Video.VideoColumns.TITLE));
                String _display_name = data.getString(data.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME));
                String _data = data.getString(data.getColumnIndex(MediaStore.Video.VideoColumns.DATA));
                String _mime_type = data.getString(data.getColumnIndex(MediaStore.Video.VideoColumns.MIME_TYPE));

                Log.d("---", (count++) + "---------------------------------------------------");
                Log.d("---", "title:" + _title + "\n");
                Log.d("---", "display_name:" + _display_name + "\n");
                Log.d("---", "data:" + _data + "\n");
                Log.d("---", "mime_type:" + _mime_type + "\n");
            }

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
