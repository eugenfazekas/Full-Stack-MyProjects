import { MessageModel } from "./message";

export class ChatModel {

    constructor(
        public username?: string,
        public messages?: MessageModel[],
        public newMessage?: boolean,
        public newMessageCounter?: number
       ) {}
}