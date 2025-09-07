import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: [
        {   path: '/',
            name: 'home',
            component: HomeView, // HomeView 컴포넌트
        },
        {
            path: '/hotelsearch',
            name: 'hotelsearch',
            component: HotelSearchView, // HotelSearchView 컴포넌트
        },
        {
            path: '/hoteldetail',
            name: 'hoteldetail',
            component: HotelDetailView, // HotelDetailView 컴포넌트
        },
        {
            path: '/paymentpage',
            name: 'paymentpage',
            component: PaymentPageView, // PaymentPageView 컴포넌트
        },
        {
            path: '/favorites',
            name: 'favorites',
            component: FavoritesView, // FavoritesView 컴포넌트
        },
        {
            path: '/account',
            name: 'account',
            component: AccountView, // AccountView 컴포넌트
        },
        {
            path: '/reservation',
            name: 'reservation',
            component: ReservationView, // ReservationView 컴포넌트
        },
        {
            path: '/paymentadd',
            name: 'paymentadd',
            component: PaymentAddView, // PaymentAddView 컴포넌트
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView, // LoginView 컴포넌트
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignUpView, // SignUpView 컴포넌트
        },
        {
            path: '/firstpaymentadd',
            name: 'firstpaymentadd',
            component: FirstPaymentAddView, // FirstPaymentAddView 컴포넌트
        },
        {
            path: '/findpw',
            name: 'findpw',
            component: FindpwView, // FindpwView 컴포넌트
        },
        {
            path: '/identification',
            name: 'identification',
            component: IdentificationView, // IdentificationView 컴포넌트
        },
        {
            path: '/resetpw',
            name: 'resetpw',
            component: ResetpwView, // ResetpwView 컴포넌트
        },
    ],
});

export default router;