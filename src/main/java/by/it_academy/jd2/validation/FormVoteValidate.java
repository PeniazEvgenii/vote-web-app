package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.validation.api.IValidate;

public class FormVoteValidate implements IValidate<InfoFromUserDTO> {
    private static final FormVoteValidate INSTANCE= new FormVoteValidate();

    private FormVoteValidate(){}


    public static FormVoteValidate getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult valid(InfoFromUserDTO infoFromUserDTO) {
        ValidationResult validationResult = new ValidationResult();

        if(infoFromUserDTO.getSinger() == null) {
            validationResult.addError(new Err("singer_incorrect","Singer is not chosen"));
        }

        String[] janres = infoFromUserDTO.getJanres();
        if(janres == null || janres.length < 3) {
            validationResult.addError(new Err("janres_incorrect", "You need to choose 3 or more janres"));
        }

        if(infoFromUserDTO.getInfo().isBlank()) {
            validationResult.addError(new Err("info_incorrect", "Field with information is empty"));
        }

        return validationResult;
    }
}
