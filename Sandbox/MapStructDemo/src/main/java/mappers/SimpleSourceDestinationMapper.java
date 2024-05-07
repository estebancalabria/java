package mappers;

import org.mapstruct.Mapper;
import models.*;

@Mapper
public interface SimpleSourceDestinationMapper {
    Destination sourceToDestination(Source source);
    Source destinationToSource(Destination destination);
}