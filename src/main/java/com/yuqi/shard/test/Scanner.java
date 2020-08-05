package com.yuqi.shard.test;

import com.yuqi.shard.aspect.ShardMapper;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

public class Scanner extends ClassPathBeanDefinitionScanner {

    private Class type;

    public Scanner(BeanDefinitionRegistry registry,Class<? extends Annotation> type){
        super(registry,false);
        this.type = type;
    }
    //注册 过滤器
    public void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(type));
    }

    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public Set<BeanDefinitionHolder> scan0(String... basePackages) {
        Set<BeanDefinitionHolder> holders = super.doScan(basePackages);


        return holders;
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        annotationMetadata.getAnnotationTypes().contains(ShardMapper.class);
        return false;
    }



}
