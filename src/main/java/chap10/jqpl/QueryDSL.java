package chap10.jqpl;

import chap07.join_strategy.Album;
import chap07.join_strategy.Item;
import chap07.join_strategy.JoinedStrategyApp;
import chap08.proxy.Dish;
import chap08.proxy.ProxyApp;
import chap08.proxy.QDish;
import com.mysema.query.QueryModifiers;
import com.mysema.query.SearchResults;
import com.mysema.query.jpa.impl.JPAQuery;
import com.sun.org.apache.bcel.internal.generic.ALOAD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static chap08.proxy.QDish.dish;
import static chap07.join_strategy.QItem.item;
import static chap07.join_strategy.QAlbum.album;

public class QueryDSL {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
//        ProxyApp.saveWithCascade(em);
        JoinedStrategyApp.populateItem(em);
//        queryDSLTest(em);
//        staticImportTest(em);
//        pagingAndOrderTest(em);
        pagingWithSearchResults(em);
    }

    public static void queryDSLTest(EntityManager em){
        JPAQuery query = new JPAQuery(em);
        QDish qDish = new QDish("d");

        List<Dish> dishes =
                query.from(qDish)
                .where(qDish.name.eq("a"))
                .orderBy(qDish.name.desc())
                .list(qDish);

        dishes.forEach(d -> System.out.println(d.toString()));
    }

    public static void staticImportTest(EntityManager em){
        JPAQuery query = new JPAQuery(em);
        String param1 = "radiohead";

        List<Album> albums =
                query.from(album)
                .where(album.artist.eq(param1).or(album.artist.startsWith("de")))
                .orderBy(album.name.desc(), album.price.asc())
                .list(album);

        albums.forEach(a-> System.out.println(a.toString()));
    }

    public static void pagingAndOrderTest(EntityManager em){
        JPAQuery query = new JPAQuery(em);
        QueryModifiers queryModifiers = new QueryModifiers(3L, 0L);
        List<Album> albums =
                query.from(album)
                .orderBy(album.artist.asc(), album.price.asc())
//                .offset(1L)
//                .limit(2L)
                        .restrict(queryModifiers)
                .list(album);

        albums.forEach(a -> System.out.println(a.toString()));


    }

    public static void pagingWithSearchResults(EntityManager em){
        JPAQuery query = new JPAQuery(em);
        QueryModifiers queryModifiers = new QueryModifiers(2L, 0L);
        SearchResults<Album> results =
                query.from(album)
                .orderBy(album.artist.asc())
                .restrict(queryModifiers)
                .listResults(album);

        System.out.println("limit : " +results.getLimit());
        System.out.println("offset : " + results.getOffset());
        results.getResults().forEach(a -> System.out.println(a.toString()));
    }

    public static void joinTest(EntityManager em){
        JPAQuery query = new JPAQuery(em);

    }
}
