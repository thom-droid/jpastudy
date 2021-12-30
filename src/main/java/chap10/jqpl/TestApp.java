package chap10.jqpl;

import chap05.entity_relation.Course;
import chap05.entity_relation.CourseDto;
import chap05.entity_relation.Professor;
import chap08.proxy.Dish;
import chap08.proxy.ProxyApp;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                //...
            }
        });


        chap05.entity_relation.TestApp.testSave(em);
//        jpqlTest(em);
//        searchWithCriteria(em);
//        projectionTest(em);
//        projectionScala(em);
//        projectionMultipleValue(em);
        projectWithNew(em);

        ProxyApp.saveWithCascade(em);
        namedQueryTest(em);


    }



    public static void jpqlTest(EntityManager em){

        String jqpl = "select c from Course c where c.name = 'french'";
        List<Course> courses = em.createQuery(jqpl, Course.class).getResultList();
        courses.forEach(c -> System.out.println(c.toString()));

    }

    public static void searchWithCriteria(EntityManager em){

        //preparing criteria
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> query = cb.createQuery(Course.class);

        //set root where to start a query
        Root<Course> c = query.from(Course.class);

        //query
        CriteriaQuery<Course> cq =
                query.select(c)
                        .where(cb.equal(c.get("name"), "french"));

        List<Course> resultList = em.createQuery(cq).getResultList();

        for (Course course : resultList) {

            System.out.println(course.toString());

        }
    }

    public static void projectionTest(EntityManager em){
        Query students =
                em.createQuery("SELECT c.students FROM Course c");

//        List<Student> studentTypedQuery = em.createQuery("SELECT c.students FROM Course c", Student.class).getResultList();
//
//        for (Student student : studentTypedQuery) {
//            System.out.println(student.toString());
//        }

        Professor professorTypedQuery = em.createQuery("SELECT c.professor FROM Course c", Professor.class).getSingleResult();
        System.out.println(professorTypedQuery.toString());
    }

    public static void projectionScala(EntityManager em){
        List<String> lectureNames = em.createQuery("SELECT c.name FROM Course c", String.class).getResultList();
        lectureNames.forEach(System.out::println);
    }

    public static void projectionMultipleValue(EntityManager em){
        List<Object[]> query = em.createQuery("SELECT c.name, c.seats, c.professor FROM Course c").getResultList();

        query.forEach(q-> System.out.println(Arrays.toString(q)));

    }

    public static void projectWithNew(EntityManager em){
        List<CourseDto> list = em
                .createQuery("SELECT new chap05.entity_relation.CourseDto(c.name, c.professor.professorName) " +
                        "FROM Course c", CourseDto.class)
                .getResultList();

        list.forEach(d -> System.out.println(d.toString()));
    }

    public static void namedQueryTest(EntityManager em){
        String ingredient = "진라면";
        List<Dish> results = em.createNamedQuery("Dish.findByIngredient", Dish.class)
                .setParameter("ingredient", ingredient)
                .getResultList();

        results.forEach((d -> System.out.println(d.toString())));

    }
}
