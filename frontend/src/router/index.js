import { createRouter, createWebHistory } from 'vue-router';

// 뷰 컴포넌트 import
import HomeView from '@/views/HomeView.vue';
import HotelSearchView from '@/views/HotelSearchView.vue';
import HotelDetailView from '@/views/HotelDetailView.vue';
import PaymentPageView from '@/views/PaymentPageView.vue';
import FavoritesView from '@/views/FavoritesView.vue';
import AccountView from '@/views/AccountView.vue';
import ReservationView from '@/views/ReservationView.vue';
import PaymentAddView from '@/views/PaymentAddView.vue';
import LoginView from '@/views/LoginView.vue';
import SignUpView from '@/views/SignUpView.vue';
import FirstPaymentAddView from '@/views/FirstPaymentAddView.vue';
import FindpwView from '@/views/FindpwView.vue';
import IdentificationView from '@/views/IdentificationView.vue';
import ResetpwView from '@/views/ResetpwView.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL), // Vue 3 + Vite에서는 process.env 대신 import.meta.env 사용
    routes: [
        { path: '/', name: 'home', component: HomeView },
        { path: '/hotelsearch', name: 'hotelsearch', component: HotelSearchView },
        { path: '/hoteldetail', name: 'hoteldetail', component: HotelDetailView },
        { path: '/paymentpage', name: 'paymentpage', component: PaymentPageView },
        { path: '/favorites', name: 'favorites', component: FavoritesView },
        { path: '/account', name: 'account', component: AccountView },
        { path: '/reservation', name: 'reservation', component: ReservationView },
        { path: '/paymentadd', name: 'paymentadd', component: PaymentAddView },
        { path: '/login', name: 'login', component: LoginView },
        { path: '/signup', name: 'signup', component: SignUpView },
        { path: '/firstpaymentadd', name: 'firstpaymentadd', component: FirstPaymentAddView },
        { path: '/findpw', name: 'findpw', component: FindpwView },
        { path: '/identification', name: 'identification', component: IdentificationView },
        { path: '/resetpw', name: 'resetpw', component: ResetpwView },
    ],
});

export default router;
