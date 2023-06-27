package org.example;


public class PostController {
public static void UpdateStatus(PostPOJO p, PostStatus ps){
    p.setStatus(ps);
    System.out.println(ps+ " Статус обновлен");
}
}
