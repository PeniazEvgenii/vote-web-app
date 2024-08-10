package by.it_academy.jd2.validation;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.validation.api.IValidate;

public class FormVoteValidate implements IValidate<InfoFromUserDTO> {
    private static final FormVoteValidate INSTANCE = new FormVoteValidate();
    private static final int MIN_COUNT_JANRES = 3;

    private FormVoteValidate(){}

    public static FormVoteValidate getInstance() {
        return INSTANCE;
    }

    /**
     * Метод для валидации информации, полученной от пользователя
     * @param infoFromUserDTO - объект InfoFromUserDTO с информацией
     * @return объект ValidationResult, включающий лист ошибок List<Err> errors
     */
    @Override
    public ValidationResult valid(InfoFromUserDTO infoFromUserDTO) {
        ValidationResult validationResult = new ValidationResult();

        if(infoFromUserDTO.getSinger() == null) {
            validationResult.addError(new Err("singer_incorrect","Singer is not chosen", "Не выбран исполнитель"));
        }

        String[] janres = infoFromUserDTO.getJanres();
        if(janres == null || janres.length < MIN_COUNT_JANRES) {
            validationResult.addError(new Err("janres_incorrect", "You need to choose " + MIN_COUNT_JANRES + " or more janres",
                    "Необходимо выбрать " + MIN_COUNT_JANRES + " или более жанра"));
        }

        if(infoFromUserDTO.getInfo().isBlank()) {
            validationResult.addError(new Err("info_incorrect", "Field with information is empty", "Поле с информацией не заполнено"));
        }

        return validationResult;
    }
}
