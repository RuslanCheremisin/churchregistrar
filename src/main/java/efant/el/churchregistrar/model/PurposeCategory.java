package efant.el.churchregistrar.model;

public enum PurposeCategory {
    GENERAL_PURPOSE("Общие нужды"),
    YOUTH_GROUP("Молодёжные собрания"),
    HOME_GROUPS("Домашние группы"),
    INNER_CHARITY("Благотворительность для членов церкви"),
    OUTER_CHARITY("Благотворительность за пределами церкви"),
    SUNDAY_SCHOOL("Воскресная школа"),
    WORSHIP_BAND("Группа прославления"),
    CHURCH_MINISTERS_SUPPORT("Поддержка служителей церкви"),
    PASTOR_SUPPORT("Поддержка пастора");
    private final String translation;
    PurposeCategory(String translation){
        this.translation = translation;
    }
}
