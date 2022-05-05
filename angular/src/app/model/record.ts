export class Record {
    public id: number |null = null
    public dateTime : Date| null = null 
    public description: string |null = null
    

    constructor(
        id: number|null = null,
        dateTime : Date | null =  null,
        description : string|null = null)
    { 
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
    }
}