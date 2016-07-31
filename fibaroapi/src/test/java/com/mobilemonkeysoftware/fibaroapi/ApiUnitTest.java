//package com.mobilemonkeysoftware.fibaroapi;
//
//import android.support.annotation.NonNull;
//
//import com.mobilemonkeysoftware.fibaroapi.model.SettingsInfo;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//
//import java.util.List;
////import java.util.Set;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import dagger.Component;
//import dagger.Module;
//import rx.Observable;
//import rx.observers.TestSubscriber;
//
////import static android.test.MoreAsserts.assertNotEmpty;
//import static junit.framework.Assert.assertNotNull;
//
///**
// * Created by AR on 31/07/2016.
// */
//@RunWith(RobolectricTestRunner.class)
//@Config(manifest = Config.NONE)
//public class ApiUnitTest {
//
//    @Inject Api api;
//
//    @Before public void setUp() {
//
//        TestApiComponent component = DaggerTestApiComponentt.builder()
//                .apiModule(new ApiModule("http://styx.fibaro.com:9999/api/", "admin", "admin"))
//                .build();
//        component.inject(this);
//    }
//
//    @Singleton
//    @Component(modules = {TestApiModule.class})
//    public interface TestApiComponent {
//        void inject(ApiUnitTest unitTest);
//    }
//
//    @Module
//    public class TestApiModule extends ApiModule {
//
//        public TestApiModule(@NonNull String url, @NonNull String login, @NonNull String password) {
//            super(url, login, password);
//        }
//    }
//
//    @Test public void getSettingsInfo() throws Exception {
//
//        Observable<SettingsInfo> observable = api.getSettingsInfo();
//        TestSubscriber<SettingsInfo> subscriber = new TestSubscriber<>();
//        observable.subscribe(subscriber);
//
//        subscriber.assertCompleted();
//        subscriber.assertNoErrors();
//        List<SettingsInfo> onNextEvents = subscriber.getOnNextEvents();
//        assertNotNull(onNextEvents);
//        assertNotEmpty(onNextEvents);
//        assertNotNull(onNextEvents.get(0));
//    }
//
//}
