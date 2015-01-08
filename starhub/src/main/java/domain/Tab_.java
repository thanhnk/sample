package domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Tab.class)
public class Tab_ {
	public static volatile SingularAttribute<Tab, String> title;
	public static volatile SingularAttribute<Tab, String> text;
	public static volatile SingularAttribute<Tab, Long> id;
	public static volatile SingularAttribute<Tab, Long> parentId;
	public static volatile SingularAttribute<Tab, Integer> version;
}
