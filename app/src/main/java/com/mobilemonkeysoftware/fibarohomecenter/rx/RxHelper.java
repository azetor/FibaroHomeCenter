package com.mobilemonkeysoftware.fibarohomecenter.rx;

import com.mobilemonkeysoftware.fibaroapi.model.Room;
import com.mobilemonkeysoftware.fibaroapi.model.Section;
import com.mobilemonkeysoftware.fibarohomecenter.ui.model.SectionItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by AR on 02/08/2016.
 */
public final class RxHelper {

    private RxHelper() {
    }

    public static Observable<List<SectionItem>> loadSectionItems(Observable<List<Section>> sectionsObservable, Observable<List<Room>> roomsObservable) {
        return sectionsObservable
                .flatMap(new Func1<List<Section>, Observable<List<SectionItem>>>() {
                    @Override
                    public Observable<List<SectionItem>> call(final List<Section> sections) {
                        return Observable.create(new Observable.OnSubscribe<List<SectionItem>>() {
                            @Override
                            public void call(Subscriber<? super List<SectionItem>> subscriber) {

                                try {
                                    List<SectionItem> results = new ArrayList<>();
                                    for (Section section : sections) {
                                        results.add(SectionItem.create(section));
                                    }
                                    subscriber.onNext(results);
                                    subscriber.onCompleted();
                                } catch (Exception e) {
                                    subscriber.onError(e);
                                }
                            }
                        });
                    }
                })
                .zipWith(roomsObservable, new Func2<List<SectionItem>, List<Room>, List<SectionItem>>() {
                    @Override
                    public List<SectionItem> call(List<SectionItem> sectionItems, List<Room> rooms) {

                        for (SectionItem item : sectionItems) {
                            for (Room room : rooms) {
                                if (item.section().id() == room.sectionId()) {
                                    item.rooms().add(room);
                                }
                            }
                        }
                        return sectionItems;
                    }
                });
    }

}
