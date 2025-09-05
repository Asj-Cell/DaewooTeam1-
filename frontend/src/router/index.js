import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: [
        { path: '/',
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
            component: HotelDetailView,
        },
        {
            path: '/paymentpage',
            name: 'paymentpage',
            component: PaymentPageView,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },
        {
            path: '/',
            name: '',
            component: View,
        },

    ],
});

export default router;