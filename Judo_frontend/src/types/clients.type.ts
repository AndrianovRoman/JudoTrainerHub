export type ClientsType = {
  id: number,
  firstName: string,
  lastName: string,
  email: string,
  phoneNumber: string,
  isTrainer: boolean,
  qualificationId?: number | null,
  roleId: number,

  qualificationStr?: string,
}
