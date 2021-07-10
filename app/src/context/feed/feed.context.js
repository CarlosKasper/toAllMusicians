import createGlobalState from 'react-create-global-state';

const stringifyFeed = localStorage.getItem('feed');

const feed = stringifyFeed && JSON.parse(stringifyFeed);

const [useGlobalFeed, FeedProvider] = createGlobalState(feed);

export { useGlobalFeed, FeedProvider };
