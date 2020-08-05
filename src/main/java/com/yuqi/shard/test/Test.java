package com.yuqi.shard.test;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;

public class Test implements ImportBeanDefinitionRegistrar {

//    private static boolean res = true;

    public static void main(String[] args) {
        System.out.println(canFinish(3, new int[][]{{1, 0}, {1, 2}, {2, 1}}));
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        annotationMetadata.getAnnotationTypes();
//        {
//            basePackageClass -> 包路径
//            Scanner scanner = new Scanner(beanDefinitionRegistry);
//            scanner.scan0(包路径);
//        }
        ArrayList<Integer>[] list = new ArrayList[0];
    }

    private static boolean res = true;

    private static boolean[] temp;

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        boolean[] flag = new boolean[numCourses];
        temp = new boolean[numCourses];
        ArrayList<Integer>[] list = new ArrayList[numCourses];
        for (int[] prerequisite : prerequisites) {
            if (list[prerequisite[0]] == null) {
                list[prerequisite[0]] = new ArrayList<>();
            }
            list[prerequisite[0]].add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!res) {
                return false;
            }
            if (list[i] != null && !temp[i]) {
                dfs(list, flag, i);
            }
        }
        return true;
    }


    public static void dfs(ArrayList<Integer>[] list, boolean[] flag, int i) {
        temp[i] = true;
        if (flag[i]) {
            res = false;
            return;
        }
        if (list[i] == null) {
            return;
        }
        flag[i] = true;
        for (int j = 0; j < list[i].size(); j++) {
            dfs(list, flag, list[i].get(j));
        }
        flag[i] = false;
    }

}
