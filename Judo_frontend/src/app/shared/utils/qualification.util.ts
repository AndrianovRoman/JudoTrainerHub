
export class QualificationUtil {
  static getQualificationName(nameNum: number | undefined | null): string {

    let name: string = '';

    switch (nameNum) {
      case 1:
        name = 'Кандидат Мастера Спорта';
        break;
      case 2:
        name = 'Мастер Спорта';
        break;
      case 3:
        name = 'Мастер Спорта Международного Класса';
        break;
      case 4:
        name = 'Заслуженный Мастер Спорта';
        break;
    }

    return name;
  }
}
