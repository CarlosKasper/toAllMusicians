import axios from 'axios';
import { useCallback } from 'react';

export function useAxios(baseURL, headers) {
	const axiosInstance = axios.create({
		baseURL,
		headers,
	});

	return useCallback(axiosInstance, [baseURL, headers]);
}
