package com.mycompany.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            int opcion;
            do {
                mostrarMenu();
                System.out.print("Ingrese su opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner

                switch (opcion) {
                    case 1:
                        insertarNuevaFila(session, scanner);
                        break;
                    case 2:
                        borrarFila(session, scanner);
                        break;
                    case 3:
                        consultarDatos(session, scanner);
                        break;
                    case 4:
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 4);
        }
    }

    private static void mostrarMenu() {
        System.out.println("MENÚ");
        System.out.println("1- Inserción de nuevas filas");
        System.out.println("2- Borrado de filas");
        System.out.println("3- Consultas");
        System.out.println("4- Fin");
    }

    private static void insertarNuevaFila(Session session, Scanner scanner) {
        System.out.println("1. Inserción autor");
        System.out.println("2. Inserción libro");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                insertarAutor(session, scanner);
                break;
            case 2:
                insertarLibro(session, scanner);
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void insertarAutor(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        System.out.print("Ingrese DNI del autor: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese nombre del autor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nacionalidad del autor: ");
        String nacionalidad = scanner.nextLine();

        Autor autor = new Autor(dni, nombre, nacionalidad);
        session.save(autor);

        transaction.commit();
        System.out.println("Autor insertado correctamente.");
    }

    private static void insertarLibro(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese precio del libro: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner

        System.out.print("Ingrese DNI del autor del libro: ");
        String dniAutor = scanner.nextLine();
        Autor autor = session.get(Autor.class, dniAutor);

        if (autor != null) {
            Libro libro = new Libro(titulo, precio, autor);
            session.save(libro);
            transaction.commit();
            System.out.println("Libro insertado correctamente.");
        } else {
            transaction.rollback();
            System.out.println("No se encontró el autor con DNI: " + dniAutor);
        }
    }

    private static void borrarFila(Session session, Scanner scanner) {
        System.out.println("1. Borrado libro");
        System.out.println("2. Borrado autor");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                borrarLibro(session, scanner);
                break;
            case 2:
                borrarAutor(session, scanner);
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void borrarLibro(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        System.out.print("Ingrese ID del libro a borrar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        Libro libro = session.get(Libro.class, id);
        if (libro != null) {
            session.delete(libro);
            transaction.commit();
            System.out.println("Libro borrado correctamente.");
        } else {
            transaction.rollback();
            System.out.println("No se encontró el libro con ID: " + id);
        }
    }

    private static void borrarAutor(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        System.out.print("Ingrese DNI del autor a borrar: ");
        String dni = scanner.nextLine();

        Autor autor = session.get(Autor.class, dni);
        if (autor != null) {
            session.delete(autor);
            transaction.commit();
            System.out.println("Autor borrado correctamente.");
        } else {
            transaction.rollback();
            System.out.println("No se encontró el autor con DNI: " + dni);
        }
    }

    private static void consultarDatos(Session session, Scanner scanner) {
        System.out.println("1. Visualizar datos de un libro por título");
        System.out.println("2. Visualizar libros de un autor por nombre");
        System.out.println("3. Visualización de todos los libros");
        System.out.println("4. Visualización de todos los autores con sus libros");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion) {
            case 1:
                consultarLibroPorTitulo(session, scanner);
                break;
            case 2:
                consultarLibrosDeAutor(session, scanner);
                break;
            case 3:
                consultarTodosLosLibros(session);
                break;
            case 4:
                consultarTodosLosAutoresConLibros(session);
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void consultarLibroPorTitulo(Session session, Scanner scanner) {
        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();

        Libro libro = (Libro) session.createQuery("FROM Libro WHERE titulo = :titulo")
                .setParameter("titulo", titulo)
                .uniqueResult();

        if (libro != null) {
            System.out.println(libro);
        } else {
            System.out.println("No se encontró ningún libro con el título: " + titulo);
        }
    }

    private static void consultarLibrosDeAutor(Session session, Scanner scanner) {
        System.out.print("Ingrese nombre del autor: ");
        String nombreAutor = scanner.nextLine();

        Autor autor = (Autor) session.createQuery("FROM Autor WHERE nombre = :nombre")
                .setParameter("nombre", nombreAutor)
                .uniqueResult();

        if (autor != null) {
            System.out.println("Libros del autor " + nombreAutor + ":");
            autor.getLibros().forEach(System.out::println);
        } else {
            System.out.println("No se encontró ningún autor con el nombre: " + nombreAutor);
        }
    }

    private static void consultarTodosLosLibros(Session session) {
        System.out.println("Lista de todos los libros:");
        session.createQuery("FROM Libro", Libro.class).getResultList().forEach(System.out::println);
    }

    private static void consultarTodosLosAutoresConLibros(Session session) {
        System.out.println("Lista de todos los autores con sus libros:");
        session.createQuery("FROM Autor", Autor.class).getResultList().forEach(autor -> {
            System.out.println(autor);
            autor.getLibros().forEach(System.out::println);
        });
    }
}
