UPDATE ministeriodb.celulas
SET
    dataExclusao = NOW()
WHERE id = :id;
