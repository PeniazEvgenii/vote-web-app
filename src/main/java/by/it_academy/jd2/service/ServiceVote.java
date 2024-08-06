package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.EJanres;
import by.it_academy.jd2.entity.ESingers;
import by.it_academy.jd2.service.api.IServiceVote;
import by.it_academy.jd2.storage.StorageVote;
import by.it_academy.jd2.storage.StorageForJanresVote;
import by.it_academy.jd2.storage.StorageForSingersVote;
import by.it_academy.jd2.storage.StorageForTextDateVote;
import by.it_academy.jd2.storage.api.IStorageCount;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.validation.FormVoteValidate;
import by.it_academy.jd2.validation.ValidFormException;
import by.it_academy.jd2.validation.ValidationResult;

public class ServiceVote implements IServiceVote {
    private static final IServiceVote INSTANCE = new ServiceVote();

    private final FormVoteValidate formVoteValidate = FormVoteValidate.getInstance();
    private final IVoteStorage storageVote = StorageVote.getInstance();

    private ServiceVote(){}

    public void create(InfoFromUserDTO infoFromUserDto) {
        ValidationResult validationResult = formVoteValidate.valid(infoFromUserDto);
        if(!validationResult.checkErrorEmpty()) {
            throw new ValidFormException(validationResult.getErrors());
        }

        storageVote.saveVote(infoFromUserDto);  //Моя новая хранилка под все

    }

    public static IServiceVote getInstance() {
        return INSTANCE;
    }

}
