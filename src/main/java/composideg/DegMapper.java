package composideg;

public class DegMapper {
    public static Deg degFrom(DegDTO dto) {
        return new Deg(
                dto.getDogId(),
                dto.getName(),
                new Vector(dto.getX(), dto.getY()));
    }

    public static DegDTO dtoFrom(Deg deg) {
        DegDTO dto = new DegDTO();
        dto.setDogId(deg.getDogId());
        dto.setName(deg.getName());
        dto.setX(deg.getPosition().getX());
        dto.setY(deg.getPosition().getY());
        return dto;
    }
}
