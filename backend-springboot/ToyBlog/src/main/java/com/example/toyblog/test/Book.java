package com.example.toyblog.test;

/**
 * ======================================
 * FileName : Book
 * Author : DH.Lee
 * Date : 2024-01-10
 * Note :
 * 1)
 * ======================================
 * <p>
 * Java에서 인스턴스 생성 - 프로그래머가 직접 인스턴스를 생성함.
 * <p>
 * Java에서 인스턴스 생성 - 프로그래머가 직접 인스턴스를 생성함.
 */

/**
 * Java에서 인스턴스 생성 - 프로그래머가 직접 인스턴스를 생성함.
 */

/**
 * Book 클래스는 책의 정보를 나타내며, 책의 제목과 가격 정보를 관리한다.
 */
public class Book { // Book 클래스

    // 필드
    /**
     * title 인스턴스 field(속성)
     * price 인스턴스 field(속성)
     */
    private String title; // 책의 제목. null이 될 수 없음.
    private int price; // 책의 가격. 음수가 될 수 없음.


    // 생성자
    /**
     * Book 객체를 생성하고 초기화한다.
     *
     * @param title 책의 제목
     * @param price 책의 가격
     */
    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    // Getter와 Setter 메서드
    /**
     * 책의 제목을 반환한다.
     *
     * @return 책의 제목
     */
    public String getTitle() {
        return title;
    }

    /**
     * 책의 제목을 설정한다.
     *
     * @param title 새로운 제목
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 책의 가격을 반환한다.
     *
     * @return 책의 가격
     */
    public int getPrice() {
        return price;
    }

    /**
     * 책의 가격을 설정한다. 가격은 음수가 될 수 없다.
     *
     * @param price 새로운 가격
     */

    public void setPrice(int price) {
        this.price = price;
    }

} // end of class