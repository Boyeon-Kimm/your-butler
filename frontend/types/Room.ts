export interface Room{
    roomType: String,
    complexNo: Number,
    complexName: String,
    sidoName: String,
    guName: String,
    dongName: String,
    latitude: Number,
    longitude: Number,
    descriptionTagList: String[],
    floor: Number,
    pyeong: Number,
    supplyArea: Number,
    exclusiveArea: Number,
    maintenanceFee: Number,
    color: String,
    checked: boolean,
    imageSrc: String
};

export interface RoomSearchResult{
    reslEstateName : String,
    roomTypeList : Room[]
}