import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import * as serviceWorker from './serviceWorker';
import { UserProvider } from './context';
import { FeedProvider } from './context';
import { UserSearchProvider } from './context';
import { UserInfoProvider } from './context';

ReactDOM.render(
	<BrowserRouter>
		<FeedProvider>
			<UserProvider>
				<UserSearchProvider>
					<UserInfoProvider>
						<App />
					</UserInfoProvider>
				</UserSearchProvider>
			</UserProvider>
		</FeedProvider>
	</BrowserRouter>,
	document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
serviceWorker.unregister();
