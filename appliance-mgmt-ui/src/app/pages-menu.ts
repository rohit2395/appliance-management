import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
  {
    title: 'Dashboard',
    icon: 'home-outline',
    link: '/dashboard',
    home: true,
  },
  {
    title: 'FEATURES',
    group: true,
  },
  // {
  //   title: 'Appliance Reservation',
  //   icon: 'person-outline',
  //   link: '/reservation/',
  // },
  {
    title: 'View Appliances',
    icon: 'layout-outline',
    link: '/all/',
  },
  {
    title: 'Register New Appliance',
    icon: 'shopping-cart-outline',
    link: '/new/',
  }
];
