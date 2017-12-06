/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.jrs.bookwebapp.repository;

import org.springframework.data.domain.Example;

/**
 *
 * @author Jeremy Santorelli
 * @param <T>
 */
public interface QueryByExampleExecutor<T> {

    <S extends T> S findOne(Example<S> example);

    <S extends T> Iterable<S> findAll(Example<S> example);

    // … more functionality omitted.
}
