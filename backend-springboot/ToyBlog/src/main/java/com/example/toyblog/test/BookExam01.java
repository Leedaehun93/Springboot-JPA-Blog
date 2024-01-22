package com.example.toyblog.test;

/**
 * ======================================
 * FileName : BookExam01
 * Author : DH.Lee
 * Date : 2024-01-10
 * Note :
 * 1) 스프링 탄생 배경, Bean이란?, Bean을 관리하는 컨테이너란?
 * 유튜브 동영상중
 * https://www.youtube.com/watch?v=ShR5CmEUyRY&t=105s
 * ======================================
 */
public class BookExam01 {
    public static void main(String[] args) {

        /**
         * Book 클래스의 인스턴스 생성
         */
        Book book1 = new Book("즐거운 자바", 2000);
        System.out.println(book1.getTitle());
        System.out.println(book1.getPrice());
        /**
         * 1) new Book("즐거운 자바", 2000) 생성자가 호출되면 Heap 메모리에 인스턴스가 생성된다.
         * 2) book1 은 1)에서 생성한 인스턴스를 참조한다.
         *  - book1 참조변수(레퍼런스 변수)
         */

        /**
         * 프로그래머가 직접 인스턴스를 생성 사용하는 방식
         */
        Book book = new Book("java", 10000);
        System.out.println(book.getTitle());
        System.out.println(book.getPrice());


    } // end of main
}  // end of class
