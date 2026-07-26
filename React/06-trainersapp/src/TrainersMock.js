import Trainer from "./Trainer";

// mock list of trainers used to populate the trainers list and detail pages
const TrainersMock = [
  new Trainer(1, "Anita Sharma", "anita.sharma@cognizant.com", "9876543210", "React", ["JavaScript", "React", "Redux"]),
  new Trainer(2, "Rahul Verma", "rahul.verma@cognizant.com", "9876543211", "Angular", ["TypeScript", "Angular", "RxJS"]),
  new Trainer(3, "Divya Iyer", "divya.iyer@cognizant.com", "9876543212", "Node.js", ["Node.js", "Express", "MongoDB"])
];

export default TrainersMock;
