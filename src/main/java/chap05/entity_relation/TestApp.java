package chap05.entity_relation;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        testSave(em);
//        testFind(em);
//        queryLogicJoin(em);
//        updateRelation(em);
//        deleteRelation(em);
//        deleteEntity(em);
        findStudentsInCourse(em);
        tx.commit();
        em.close();

        EntityManager em2 = emf.createEntityManager();
        Course course = em2.find(Course.class, "course1");
        for (Student s: course.getStudents()){
            System.out.println(s.getName());
        }
    }

    private static void testSave(EntityManager em) {

        Course course = new Course("course1", "french", 25);

        em.persist(course);

        Student student1 = new Student("st1","miso");
        student1.setCourse(course);
        em.persist(student1);

        Student student2 = new Student("st2", "hyeonsu");
        student2.setCourse(course);
        em.persist(student2);

    }

    private static void testFind(EntityManager em) {

        Student student1 = em.find(Student.class, "st1");
        Course course1 = student1.getCourse();
        System.out.println(course1.getName());

    }

    private static void queryLogicJoin(EntityManager em) {

        String jpql = "select s from Student s join s.course c where c.name=:courseName";

        List<Student> result = em.createQuery(jpql, Student.class).setParameter("courseName", "french").getResultList();

        for(Student s : result) {
            System.out.println(s.getName());
        }
    }

    private static void updateRelation(EntityManager em) {

        Course course2 = new Course("course2", "deutsch", 20);
        em.persist(course2);

        Student student1 = em.find(Student.class, "st2");
        student1.setCourse(course2);

    }

    private static void deleteRelation(EntityManager em) {

        Student student1 = em.find(Student.class, "st2");
        student1.setCourse(null);
    }

    private static void deleteEntity(EntityManager em) {
        Student student2 = em.find(Student.class, "st1");
        student2.setCourse(null);

        Course course = em.find(Course.class, "course1");
        em.remove(course);

    }

    private static void findStudentsInCourse(EntityManager em){

        Course frenchCourse = em.find(Course.class, "course1");
        List<Student> students = frenchCourse.getStudents();
        System.out.println(students.isEmpty());
        System.out.println("출석 부르겠다?");


        for (Student s: students){
            System.out.println(s.getName());
            System.out.println("네");
        }
    }
}
