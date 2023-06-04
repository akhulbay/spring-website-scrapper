package kz.chingiz.currencyparser.mapper;

public interface Mapper<F,T> {

    T map(F object);
}
