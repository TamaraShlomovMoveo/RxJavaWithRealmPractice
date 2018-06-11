package il.co.moveo.rxjavarealmexercise;

import android.app.Application;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        initImageLoader();
    }

    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(7)
                .defaultDisplayImageOptions(options)
                .memoryCache(new WeakMemoryCache())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheExtraOptions(960, 640, null)
                .build();
        ImageLoader.getInstance().init(config);

    }

    private void initRealm() {
        Realm.init(this);

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build());
    }

}
