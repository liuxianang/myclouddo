package 设计模式;

//创建型---原型模式

public class ConcretePrototype extends Prototype {
    private String filed;

    public ConcretePrototype(String filed) {
        this.filed = filed;
    }

    @Override
    public Prototype myClone() {
        return new ConcretePrototype(filed);
    }

    @Override
    public String toString() {
        return filed;
    }
}