package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.service.api.IServiceVote;
import by.it_academy.jd2.storage.StorageForInfoFromUser;
import by.it_academy.jd2.storage.StorageForJanresVote;
import by.it_academy.jd2.storage.StorageForSingersVote;
import by.it_academy.jd2.storage.StorageForTextDateVote;
import by.it_academy.jd2.storage.api.IStorageCount;
import by.it_academy.jd2.validation.FormVoteValidate;
import by.it_academy.jd2.validation.ValidFormException;
import by.it_academy.jd2.validation.ValidationResult;

public class ServiceVote implements IServiceVote {
    private static final IServiceVote INSTANCE = new ServiceVote();

    private final FormVoteValidate formVoteValidate = FormVoteValidate.getInstance();

    private final IStorageCount<ESingers> storageForSingersVote = StorageForSingersVote.getInstance();
    private final IStorageCount<EJanres> storageForJanresVote = StorageForJanresVote.getInstance();
    private final StorageForTextDateVote storageForTextDateVote = StorageForTextDateVote.getInstance();

    private final StorageForInfoFromUser storageForInfoFromUser = new StorageForInfoFromUser();       //необработанные данные хранит вместе если надо

    private ServiceVote(){}


    public void create(InfoFromUserDTO infoFromUserDto) {
        ValidationResult validationResult = formVoteValidate.valid(infoFromUserDto);
        if(!validationResult.checkErrorEmpty()) {
            throw new ValidFormException(validationResult.getErrors());
        }

        saveResultSingers(infoFromUserDto);
        saveResultJanres(infoFromUserDto);
        saveResultTextDate(infoFromUserDto);

        storageForInfoFromUser.addInfoFromUser(infoFromUserDto);  //Если надо хранить голосование по каждому пользователю

    }

    private void saveResultSingers(InfoFromUserDTO infoFromUserDto) {
        String singer = infoFromUserDto.getSinger();
        storageForSingersVote.addElement(singer);
    }

    private void saveResultJanres(InfoFromUserDTO infoFromUserDto) {
        String[] janres = infoFromUserDto.getJanres();
            storageForJanresVote.addElement(janres);
    }

    private void saveResultTextDate(InfoFromUserDTO infoFromUserDto) {
        storageForTextDateVote.addTextTime(infoFromUserDto.getInfo(), infoFromUserDto.getDateTime());
    }

    public static IServiceVote getInstance() {
        return INSTANCE;
    }

}
