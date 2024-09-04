package by.it_academy.jd2.service;

import by.it_academy.jd2.dto.InfoFromUserDTO;
import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.mapper.MapperVoteEntity;
import by.it_academy.jd2.service.api.IServiceVote;
import by.it_academy.jd2.storage.memory.StorageVote;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.storage.api.IVoteStorage;
import by.it_academy.jd2.storage.db.VoteStorageDB;
import by.it_academy.jd2.validation.FormVoteValidate;
import by.it_academy.jd2.validation.ValidFormException;
import by.it_academy.jd2.validation.ValidationResult;

public class ServiceVote implements IServiceVote {
    private static final IServiceVote INSTANCE = new ServiceVote();

    private final FormVoteValidate formVoteValidate = FormVoteValidate.getInstance();
    private final IVoteStorage storageVote = StorageVote.getInstance();
    private final MapperVoteEntity mapperVoteEntity = MapperVoteEntity.getInstance();
    private final IStorageDB<VoteEntity> voteStorageDB = VoteStorageDB.getInstance();

    private ServiceVote(){}

    /**
     * Метод сохранения информации о голосовании в storage.
     * Метод перед передачей информации в storage осуществляет валидацию переданной информации.
     * При несоответствии данных требованиям, пробрасывается исключение ValidFormException
     * @param infoFromUserDto - объект дто с данными о голосовании
     */
    @Override
    public void create(InfoFromUserDTO infoFromUserDto) {
        ValidationResult validationResult = formVoteValidate.valid(infoFromUserDto);
        if(!validationResult.checkErrorEmpty()) {
            throw new ValidFormException(validationResult.getErrors());
        }

        VoteEntity voteEntity = mapperVoteEntity.mapFrom(infoFromUserDto);

        voteStorageDB.create(voteEntity);


      //  storageVote.saveVote(infoFromUserDto);         // в InMemory на удален

    }

    /**
     * Метод получения экземпляра синглтон класса ServiceVote
     * @return INSTANCE - объект типа IServiceVote
     */
    public static IServiceVote getInstance() {
        return INSTANCE;
    }

}
