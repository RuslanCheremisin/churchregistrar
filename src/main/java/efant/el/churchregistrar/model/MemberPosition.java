package efant.el.churchregistrar.model;

public enum MemberPosition {
    PASTOR("Пастор"),
    WORSHIP_LEADER("Лидер прославления"),
    YOUTH_PASTOR("Молодёжный пастор"),
    DEACON("Дьякон"),
    MUSICIAN("Музыкант"),
    PREACHER("Проповедник"),
    CHURCHGOER("Прихожанин");

    private String translation;

    MemberPosition(String translation){
        this.translation = translation;
    }
}
